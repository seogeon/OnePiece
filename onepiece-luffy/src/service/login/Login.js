import axios from 'axios';

class Login {

    static api = "http://13.125.248.134:5001";
    //static api = "http://localhost:5001";

    static async login(token) {

        let response =
            await axios.post(this.api + "/auth/login", {
                token: token
            }).then(response => {
                return response;
            }).catch(response => {
                return response;
            })

        return response;
    }

    static async loginCheck(userId) {

        let response =
            await axios.get(this.api + "/auth/login_check?userId=" + userId)
                .then(response => {
                    return response;
                }).catch(reason => {
                    return reason;
                })

        return response;
    }

    static async logout(userId) {

        let response =
            await axios.post(this.api + "/auth/logout", {
                userId: userId
            }).then(response => {
                return response;
            }).catch(response => {
                return response;
            })

        return response;
    }
}


export default Login;