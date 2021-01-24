
const { createProxyMiddleware } = require('http-proxy-middleware');


module.exports = function (app) {


    global.targetHost = "http://10.100.4.178:9093";
    global.servletRoot = "";
    global.proxyOpts = {
        cookieDomainRewrite: "",
        logLevel: 'debug',
        target: global.targetHost,
        changeOrigin: true,
        secure: false,
        pathRewrite: {
            '^/api': '/Resume-Bank/api'
        }
    };

    app.use(
        createProxyMiddleware('/api',
            {
                target: global.targetHost + '/',
                changeOrigin: true,
                logLevel: "debug",
                secure: true,
                pathRewrite: {
                    '^/api/': '/Resume-Bank/api/'
                },
                onProxyReq(proxyReq, req, res) {
                    proxyReq.setHeader('origin', global.targetHost);
                    //console.log(proxyReq);
                }
            }
        ));

};