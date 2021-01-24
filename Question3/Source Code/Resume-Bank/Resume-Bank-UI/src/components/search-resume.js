import React, { Component } from 'react'
import { Input, Modal, Button } from 'semantic-ui-react'
import { searchResume, downloadAsResume } from '../restAPI/rest'
import { Icon } from 'semantic-ui-react'
import { Link } from 'react-router-dom'
import ReactTable from 'react-table'
import Loader from 'react-loader-spinner'

import 'react-table/react-table.css'

var fileDownload = require('js-file-download')

export class SearchResume extends Component {

    constructor(props) {
        super(props);
        this.state = {
            searchValue: "",
            pageIndex: 0,
            pageSize: 0,
            showJSONString: false,
            JSONString: "",
            loader: false,
            resumeGridData: [],
            resumeGridColumnDefi: [{
                Header: "", sortable: true, width: 90, filter: true, Cell: (row) => {
                    return row.index + 1;
                }
            }, {
                Header: "File Name", accessor: "FileName", sortable: true, filter: true
            }, {
                Header: "#Occurrence", accessor: "NoOfOccurrence", sortable: true, filter: true
            }, {
                Header: "Download As File", accessor: "id", Cell: (row) => {
                    return <Icon as={Link} name={row.original.FilePath} onClick={this.downloadResume(row.original.FilePath, row.original.FileName)}><Icon name='arrow alternate circle down large' /></Icon>
                }
            }, {
                Header: "View As JSON", accessor: "id", Cell: (row) => {
                    return <Icon as={Link} name={row.original.FilePath} onClick={this.showResumeJSON("'" + row.original.JSON + "'")}><Icon name='js square large' /></Icon>
                }
            }]
        }
    }

    handleSearchKeyDown = (e) => {
        if (e.key === 'Enter') {
            this.setState({ loader:true })
            searchResume(this.state.searchValue).then(response => {
                this.setState({
                    resumeGridData: response.data,
                    pageSize: (response.data ? (response.data.length >= 10 ? 10 : response.data.length) : 1),
                    pageIndex: 0,
                    loader:false
                })
            });
        }
    }

    downloadResume = (filePath, fileName) => () => {
        downloadAsResume(filePath).then(response => {
            fileDownload(response.data, fileName);
        });
    }

    showResumeJSON = json => () => {
        this.setState({ showJSONString: true, JSONString: json})
    }

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value })
    }

    onPageSizeChange = (pageSize, pageIndex) => {
        this.setState({
            pageSize: pageSize,
            pageIndex: pageIndex
        })
    }

    render() {
        return (
            <div>
                <div className="fixed-header">
                    <div className="row">
                        <Input onKeyDown={this.handleSearchKeyDown} value={this.state.searchValue} name="searchValue" onChange={this.handleChange} style={{ marginLeft: '410px', width: '589px', height: '50px' }} icon='search' placeholder='Search...' />
                    </div>
                </div>
                <div class="card border border">
                    <div class="card-header rounded-0">
                        <h3 class="card-title text-dark"> <span class="left-icon"> <Icon className="fa" name='list layout' /> </span> Matched Resumes </h3>
                    </div>
                    <div class="card-body">
                        <div style={{ height: '500px', width: '100%' }} className="ag-theme-alpine">
                            <ReactTable
                                columns={this.state.resumeGridColumnDefi}
                                data={this.state.resumeGridData}
                                className="users"
                                pageSize={this.state.pageSize}
                                pageIndex={this.state.pageIndex}
                                NoDataComponent={() => null}
                                pageSizeOptions={[10, 20, 30, 40, 50]}
                                showPaginationBottom={true}
                                minRows={0}
                                onPageSizeChange={this.onPageSizeChange} />
                                <Loader type="Circles" visible={this.state.loader} color="#33373d" height={80} width={80} radius={10} style={{marginTop: '-41px', marginLeft: '624px'}} />
                        </div>
                        <Modal open={this.state.showJSONString}>
                            <Modal.Header>JSON String</Modal.Header>
                            <Modal.Content style={{ maxHeight: '300px', overflowY: 'auto' }}>
                                {this.state.JSONString}
                            </Modal.Content>
                            <Modal.Actions>
                                <Button onClick={() => this.setState({ showJSONString: false })} negative>Close</Button>
                            </Modal.Actions>
                        </Modal>
                    </div>

                </div>
            </div>
        )
    }

}