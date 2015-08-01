/**
 * Created by Sean Ross on 1/8/2015.
 */

var express = require('express');
var stylus = require('stylus');
var http = require('http');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var methodOverride = require('method-override');
var session = require('express-session');
var bodyParser = require('body-parser');
var multer = require('multer');

module.exports = function(app, config){
    app.set('port', process.env.PORT || 3000);

    app.set('views', config.rootPath + '/server/views');
    app.set('view engine', 'jade');


    app.use(stylus.middleware({
        src : config.rootPath + '/public',
        compile : function(src, path){
            return stylus(src).set('filename', path);
        }
    }));

    app.use(favicon(config.rootPath + '/public/media/favicon.ico'));
    app.use(logger('dev'));
    app.use(methodOverride());
    app.use(session({ resave: true,
        saveUninitialized: true,
        secret: 'seanross' }));
    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded({ extended: true }));
    app.use(multer({dest: config.rootPath + '/public/uploads/'}).single('photo'));
    app.use(express.static(path.join(config.rootPath, 'public')));
}