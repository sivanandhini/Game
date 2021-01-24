import axios from 'axios'
import { URI } from './API'

export function searchResume(searchText) {
    return axios(URI.resumeSearch.replace('{searchText}', searchText), {
        method: 'GET',
    });
}

export function downloadAsResume(filePath) {
    return axios(URI.resumeDownload, {
        method: 'POST',
        data: filePath,
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
    });
}

export function saveResumes(data) {
    return axios(URI.resumeAdd, {
        method: 'POST',
        data: data,
        headers: {
            'Content-Type': 'multipart/form-data'
          }
    });
}
