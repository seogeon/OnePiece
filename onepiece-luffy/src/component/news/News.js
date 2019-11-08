import React, { Component } from 'react';
import './News.css';
import {Tabs, Tab} from 'react-bootstrap';
import NewsBody from './NewsBody';

class News extends Component{

    render() {
       return (
           <div className="news-list">
               <Tabs defaultActiveKey="all" id="NewsList">
                   <Tab eventKey="all" title="ALL">
                       <NewsBody oversea='2'/>
                   </Tab>
                   <Tab eventKey="local" title="LOCAL">
                       <NewsBody oversea='0'/>
                   </Tab>
                   <Tab eventKey="global" title="GLOBAL">
                       <NewsBody oversea='1'/>
                   </Tab>
               </Tabs>
           </div>

       )
    }
}


export default News;