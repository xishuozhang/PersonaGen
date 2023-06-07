import axios from 'axios';
const api = axios.create({
    baseURL: '/'
 })
const fileApi = axios.create({
    baseURL: '/',
    responseType: 'blob',
    headers: {
      "Content-Type": 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
    }
 })
 export const getAll = () => {
    return api.get('/person/getAll')
  }

  export const getPersonById = (data) => {
   return api.get('/person/getPersonById',{ params: data })
 }

 export const getProblemById = (data) => {
  return api.get('/person/getProblemById',{ params: data })
}

export const getOtherPerson = (data) => {
  return api.get('/neo4j/getOtherPerson',{ params: data })
}

export const importExcelDeptStaff = (data) => {
  return fileApi.post('/gpt/ca/importExcelDeptStaff', data)
}