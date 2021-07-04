const webpack = require('webpack');

module.exports = {
    configureWebpack: {
        // Set up all the aliases we use in our app.
        plugins: [
            new webpack.optimize.LimitChunkCountPlugin({
                maxChunks: 6
            })
        ]
    },
    pwa: {
        name: 'Vue Argon Design',
        themeColor: '#172b4d',
        msTileColor: '#172b4d',
        appleMobileWebAppCapable: 'yes',
        appleMobileWebAppStatusBarStyle: '#172b4d'
    },
    css: {
        // Enable CSS source maps.
        loaderOptions: {
            sass: {
                prependData: `@import "@/assets/scss/style.scss";`
            }
        },
        sourceMap: process.env.NODE_ENV !== 'production'
    },
    devServer: {
        port: 8081,
        proxy: {
            '^/api': {
                target: 'http://localhost:8080',
            },
        }
    }
};