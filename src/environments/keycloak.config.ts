import {KeycloakConfig} from 'keycloak-js'

const keycloakConfig: KeycloakConfig = {
    url: "http://localhost:9090",
    realm: "car",
    clientId: "car-management"
};

export default keycloakConfig;