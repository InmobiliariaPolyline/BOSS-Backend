package com.boos.backend.Service.Implementation;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.UserCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleDriveService {

    @Value("${google.drive.folder.id:}")
    private String defaultFolderId;

    @Value("${google.drive.folder.dwg:}")
    private String folderDwgId;

    @Value("${google.drive.folder.excel:}")
    private String folderExcelId;

    @Value("${google.drive.folder.docx:}")
    private String folderDocxId;

    @Value("${google.drive.client.id:}")
    private String clientId;

    @Value("${google.drive.client.secret:}")
    private String clientSecret;

    @Value("${google.drive.refresh.token:}")
    private String refreshToken;

    private Drive getDriveService() throws Exception {
        if (clientId == null || refreshToken == null || clientId.isBlank() || refreshToken.isBlank()) {
            throw new IllegalStateException("Faltan credenciales OAuth 2.0 (client id, client secret, refresh token)");
        }

        UserCredentials credentials = UserCredentials.newBuilder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRefreshToken(refreshToken)
                .build();

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("BOSS Obras")
                .build();
    }

    public File uploadFile(MultipartFile multipartFile, String tipoArchivo) throws Exception {
        Drive drive = getDriveService();

        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getOriginalFilename());

        String targetFolder = getFolderIdForType(tipoArchivo);
        if (targetFolder != null && !targetFolder.isBlank()) {
            fileMetadata.setParents(List.of(targetFolder));
        } else {
            throw new IllegalStateException("No se ha configurado un ID de carpeta destino (google.drive.folder.id). Las Service Accounts requieren guardar en una carpeta compartida existente.");
        }

        InputStreamContent mediaContent = new InputStreamContent(
                multipartFile.getContentType(),
                multipartFile.getInputStream()
        );
        mediaContent.setLength(multipartFile.getSize());

        File uploadedFile = drive.files().create(fileMetadata, mediaContent)
                .setFields("id, name, webViewLink, webContentLink")
                .setSupportsAllDrives(true)
                .execute();

        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");
        drive.permissions().create(uploadedFile.getId(), permission)
                .setSupportsAllDrives(true)
                .execute();

        return uploadedFile;
    }

    private String getFolderIdForType(String tipoArchivo) {
        if (tipoArchivo == null) return defaultFolderId;
        return switch (tipoArchivo.toUpperCase()) {
            case "DWG" -> (folderDwgId != null && !folderDwgId.isBlank()) ? folderDwgId : defaultFolderId;
            case "EXCEL" -> (folderExcelId != null && !folderExcelId.isBlank()) ? folderExcelId : defaultFolderId;
            case "DOCX" -> (folderDocxId != null && !folderDocxId.isBlank()) ? folderDocxId : defaultFolderId;
            default -> defaultFolderId;
        };
    }
}
