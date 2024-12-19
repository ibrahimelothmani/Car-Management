import {KeycloakConfig} from 'keycloak-js'

const keycloakConfig: KeycloakConfig = {
    url: "http://localhost:9090",
    realm: "master",
    clientId: "car-management"
};

export default keycloakConfig;