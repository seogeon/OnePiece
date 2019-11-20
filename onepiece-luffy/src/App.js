import React from 'react';
import './App.css';
import FirstHeader from './component/header/FirstHeader';
import Home from "./component/page/Home";
import NewsPage from "./component/page/NewsPage";
import EventPage from "./component/page/EventPage";
import {Route} from "react-router-dom";

function App() {
    return (
        <div className="App">
            <div className="container">
                <div>
                    <FirstHeader/>
                </div>
                <Route exact path="/" component={Home}/>
                <Route path="/home" component={Home}/>
                <Route path="/notice" component={NewsPage}/>
                <Route path="/event" component={EventPage}/>
                <div id="developer-info" className="col-12">
                    개발자 : b183523@gmail.com
                </div>
            </div>
        </div>
    );
}

export default App;
