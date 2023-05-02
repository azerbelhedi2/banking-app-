import { KeycloakService } from "keycloak-angular";

export function initializeKeycloak(
  keycloak: KeycloakService
  ) {
    return () =>
      keycloak.init({
        config: {
          url: 'http://localhost:8080' ,
          realm: 'banking-app-realme',
          clientId: 'banking-app-client',
          
        },  
        loadUserProfileAtStartUp: true,
        initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false
        },
        enableBearerInterceptor: true,
        bearerPrefix: 'Bearer',
        bearerExcludedUrls: [
            '/assets',
            '/clients/public']
    });
    
}