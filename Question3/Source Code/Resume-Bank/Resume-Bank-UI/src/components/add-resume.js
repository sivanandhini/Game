import React, { Component } from 'react';
import { saveResumes } from '../restAPI/rest'
import { Form, Button, Modal } from 'semantic-ui-react'
import {Attachment} from './attachment';

export class AddResume extends Component {

    constructor(props) {
        super(props);
        this.state = {
            files: [],
            multiFiles: [],
            showAlertMsg: false
        }
    }

    removeAttachment = (index) => {
        var prevFiles = this.state.files;
        prevFiles.splice(index, 1);

        var prevMultiFiles = this.state.multiFiles;
        prevMultiFiles.splice(index, 1);

        this.setState({
            files: prevFiles,
            multiFiles: prevMultiFiles
        })
    }

    saveFile = () => {

        if (this.state.multiFiles.length > 0) {
            const data = new FormData()
            this.state.multiFiles.forEach(file => {
                data.append('file', file);
            });

            saveResumes(data).then(response => {
                this.setState({ files: [], multiFiles: [], showAlertMsg: true })
            })
        }
    }

    onFileChange = (event) => {

        let fileFormat = { fileName: event.target.files[0].name };

        var prevFiles = this.state.files;
        prevFiles.push(fileFormat);

        var prevMultiFiles = this.state.multiFiles;
        prevMultiFiles.push(event.target.files[0]);

        this.setState({
            files: prevFiles,
            multiFiles: prevMultiFiles
        })
    }

    triggerFileClick = (event) => {
        var inputField = this.refs.fileField;
        inputField.click()
    }

    render() {
        return (
            <div>
                <div className="fixed-header">
                    <div className="row">
                        <h3 style={{ marginLeft: '536px', width: '589px', height: '50px', fontFamily: 'cursive' }}>Add New Resumes</h3>
                        <Button onClick={this.saveFile} style={{height: '46px'}} positive>Save Resumes</Button>
                    </div>
                    <div class="card border border">
                        <div class="card-header rounded-0">
                            <h3 class="card-title text-dark"> </h3>
                        </div>
                        <div class="card-body">
                            <div style={{ height: '500px', width: '100%', marginLeft: '542px', marginTop: '109px' }}>
                            {this.state.files.length > 0 &&  <Form.Field style={{maxWidth: '600px', marginLeft: '-190px'}}> {
                                (() => {
                                    return this.state.files.map((item, index) => {
                                        return (
                                            <Attachment indexKey={index} {...this.props} inputs={item} 
                                            removeAttachment={() => this.removeAttachment(index)} />
                                        )
                                    })
                                })()
                            }</Form.Field> }
                                <div onClick={this.triggerFileClick}>
                                    <input type="file" ref="fileField" style={{ display: 'none' }} onChange={this.onFileChange} />
                                    <Form.Button type="file" color='blue' >Browse</Form.Button>
                                </div>
                            </div>
                            <Modal open={this.state.showAlertMsg}>
                            <Modal.Content style={{ maxHeight: '300px', overflowY: 'auto' }}>
                               Your resume(s) has been saved successfully!!!
                            </Modal.Content>
                            <Modal.Actions>
                                <Button onClick={() => this.setState({ showAlertMsg: false })} positive>Close</Button>
                            </Modal.Actions>
                        </Modal>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

}