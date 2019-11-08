import axios from 'axios';

class NoticeApi {

    static async getNewsToOversea(oversea) {

        let response =
            await axios.get("http://localhost:5001/notice?oversea=" + oversea)
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