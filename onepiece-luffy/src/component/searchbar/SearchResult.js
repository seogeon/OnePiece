import React, {Component} from 'react';
import Table from "react-bootstrap/Table";

class SearchResult extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        console.log(this.props.keyword);
        console.log(this.props.data);
        console.log(this.props.exchange);

        if (this.props.data === '0') {
            return <h1>검색하세요~</h1>
        } else {

            let noticeList = this.props.data.notice_item_list;

            return (<div>
                    <Table responsive>
                        <thead>
                        <tr>
                            <th>거래소</th>
                            <th>제 목</th>
                            <th>날 짜</th>
                        </tr>
                        </thead>
                        <tbody>
                        {noticeList.map(function (object, i) {
                            return (
                                <tr className="notice-item" onClick={() => window.open(object.url)}>
                                    <td>{object.exchange}</td>
                                    <td>{object.title}</td>
                                    <td>{object.createdAt.split("T")[0]}</td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </Table>
                </div>
            )

        }
    }

}

export default SearchResult;