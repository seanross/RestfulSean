/**
 * Created by Sean Ross on 1/8/2015.
 */

module.exports = function(app){
    app.get('/application/:one/:two', function(req, res){
        var pp = '../../public/application/' + req.params.one + "/" + req.params.two;
        console.log("Calling partial path " + pp);
        res.render(pp);
    });

    app.get('*', function(req, res){
        res.render('index');
    })
}