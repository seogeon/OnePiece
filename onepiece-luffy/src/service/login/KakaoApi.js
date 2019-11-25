import axios from 'axios';

class KakaoApi {

    static api = "https://kauth.kakao.com";
    // static rediredctUri = "http://13.125.248.134:5001/oauth"
    static rediredctUri = "http://localhost:5001/oauth"
    static restApiKey = "ac4bfcbe80a7ff1e29e3752df45dda9b";

    static async getLoginToken() {

        let response =
            await axios.get(this.api + "/oauth/authorize?client_id=" + this.restApiKey + "&redirect_uri=" + this.rediredctUri + "&response_type=code")

                .then(response => {
                    console.log(response);
                    return response;
                })
                .catch(response => {
                    console.log(response);
                    return response;
                })
        return response;
    }

}

export default KakaoApi;