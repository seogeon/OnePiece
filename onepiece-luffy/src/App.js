import React from 'react';
import './App.css';
import News from './component/news/News';
import SearchBar from './component/searchbar/SearchBar';

function App() {
    return (
        <div className="App">
            <div className="container">
                <div className="row">
                    <News/>
                </div>
                <div className="row">
                    <SearchBar/>
                </div>
            </div>
            <div className="container">
               <div id="developer-info" className="col-12">
                   개발자 : b183523@gmail.com
               </div>
            </div>
        </div>
    );
}

export default App;
