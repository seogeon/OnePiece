import axios from 'axios';

class NoticeApi {

    static async getNewsToOversea(oversea) {

        let response =
            await axios.get("http://15.164.216.197:5001/notice?oversea=" + oversea)

            .then(response => {
                console.log(response);
                return response;
            })
            .catch(response => {
                return response;
            })
        return response;
    }

    static async getExchangeList() {

        let response =
            await axios.get("http://15.164.216.197:5001/notice/exchange_list")

                .then(response => {
                    console.log(response);
                    return response;
                })
                .catch(response => {
                    return response;
                })
        return response;
    }

    static async searchNotice(exchange, keyword, page) {

        let response =
            await axios.get("http://15.164.216.197:5001/notice/" + exchange + "?keyword=" + keyword + "&page=" + page)

                .then(response => {
                    console.log(response);
                    return response;
                })
                .catch(response => {
                    return response;
                })
        return response;
    }
}



export default NoticeApi;