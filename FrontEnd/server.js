/**
 * Created by Sean Ross on 14/7/2015.
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
var errorHandler = require('errorhandler');

var env = process.env.NODE_ENV = process.env.NODE_ENV || 'development';

var app = express();



app.use(stylus.middleware({
    src : __dirname + '/public',
    compile : function(src, path){
        return stylus(src).set('filename', path);
    }
}));


app.set('port', process.env.PORT || 3000);
app.set('views', path.join(__dirname, 'server/views'));
app.set('view engine', 'jade');
app.use(favicon(__dirname + '/public/media/favicon.ico'));
app.use(logger('dev'));
app.use(methodOverride());
app.use(session({ resave: true,
    saveUninitialized: true,
    secret: 'uwotm8' }));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(multer());
app.use(express.static(path.join(__dirname, 'public')));

var server = app.listen(app.get('port'), function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log('Example app listening at http://%s:%s', host, port);
});


app.get('/partials/:one/:two', function(req, res){

    var pp = '../../public/app/' + req.params.one + "/" + req.params.two;
    console.log("Calling partial path " + pp);
    res.render(pp);
});

app.get('*', function(req, res) {
    //res.send('hello world');
    res.render('index');
});

