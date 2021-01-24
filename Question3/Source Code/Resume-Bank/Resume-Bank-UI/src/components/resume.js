import React, { Component } from 'react'
import { Container, List, Menu, Segment } from 'semantic-ui-react'
import { AddResume } from '../components/add-resume'
import { SearchResume } from '../components/search-resume'
import { Route, Switch, Link, HashRouter } from 'react-router-dom'

export class Resume extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <div className="position-fixed header-section">
          <div className="row">
            <div className="col-lg-2 logo-bg">
              <a className="logotxt"> Resume Bank </a>
            </div>
            <div className="col-lg px-0">
              <Menu>
                <Menu.Item as={Link} to="/resume/search">Search Resume</Menu.Item>
                <Menu.Item as={Link} to="/resume/add">Add Resume</Menu.Item>
              </Menu>
            </div>
          </div>
        </div>

        <div style={{ marginTop: '7em' }} className="container-fluid">

          <HashRouter>
            <Switch>
              <Route path="/resume/add" exact render={() => <AddResume />} />
              <Route path="/resume/search" exact render={() => <SearchResume />} />
            </Switch>
          </HashRouter>

        </div>
        <Segment inverted fixed='bottom' vertical style={{ position: 'fixed', width: '100%', left: '0', bottom: '0' }}>
          <Container textAlign='center'>
            <List horizontal inverted divided link size='small'>
            </List>
          </Container>
        </Segment>
      </div>
    )
  }

}