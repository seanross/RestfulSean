/**
 * Created by Sean Ross on 1/8/2015.
 */

var express = require("express");

var app = express();

var env = process.env.NODE_ENV = process.env.NODE_ENV || 'dev';

var config = require('./server/configs/config')[env];

require('./server/configs/express')(app, config);

require('./server/configs/routes')(app);

app.listen(config.port);

console.log('Listening on port ' + config.port + '...');