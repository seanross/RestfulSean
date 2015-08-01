/**
 * Created by Sean Ross on 1/8/2015.
 */

var path = require('path');
var rootPath = path.normalize(__dirname + '/../../');


module.exports = {
    dev : {
        resourcePath : 'http://localhost:8080/BackEnd',
        rootPath : rootPath,
        port: process.env.PORT || 3000
    },
    prod : {
        resourcePath : 'http://192.168.110.15:8080/BackEnd',
        rootPath : rootPath,
        port: process.env.PORT || 80
    }
}