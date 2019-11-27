import axios from 'axios';
import ServerConstant from "../constant/ServerConstant";

class NoticeApi {

    static api = ServerConstant.api;

    static async getNewsToOversea(oversea) {

        console.log(this.api);

        let response =
            await axios.get(this.api + "/notice?oversea=" + oversea)

                .then(response => {
                    // console.log(response);
                    return response;
                })
                .catch(response => {
                    return response;
                })
        return response;
    }

    static async getExchangeList() {

        let response =
            await axios.get(this.api + "/notice/exchange_list")

                .then(response => {
                    // console.log(response);
                    return response;
                })
                .catch(response => {
                    return response;
                })
        return response;
    }

    static async searchNotice(exchange, kind, keyword, page) {

        let response =
            await axios.get(this.api + "/notice/" + exchange + "?keyword=" + keyword + "&page=" + page + "&kind=" + kind)

                .then(response => {
                    //] console.log(response);
                    return response;
                })
                .catch(response => {
                    return response;
                })
        return response;
    }
}


export default NoticeApi;