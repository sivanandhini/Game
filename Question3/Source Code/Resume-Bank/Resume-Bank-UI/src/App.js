import React from 'react';
import { Route, Switch, HashRouter } from 'react-router-dom';
import {Resume} from './components/resume';
import './semantic/dist/semantic.min.css';
import './App.css';
import './css/core.css'

function App() {
  return (
    <HashRouter>
        <Switch>
          <Route path="/" component={Resume}/>
          <Route path="/resume" component={Resume}/>
        </Switch>
     </HashRouter>
  );
}

export default App;
