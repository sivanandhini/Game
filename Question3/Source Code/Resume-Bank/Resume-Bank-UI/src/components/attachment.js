import React, { Component } from 'react'
import { Label, Icon } from 'semantic-ui-react'

export class Attachment extends Component {

    render() {
        return (
            <Label style={{marginBottom: '19px'}}>{this.props.inputs.fileName}<Icon style={{marginLeft: '9px'}} name='times' onClick={this.props.removeAttachment}/></Label>
        )       
    }

}