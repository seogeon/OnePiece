import axios from 'axios';

class NoticeApi {

    static api = "http://13.125.248.134:5001";
    //static api = "http://localhost:5001";

    static async getNewsToOversea(oversea) {

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