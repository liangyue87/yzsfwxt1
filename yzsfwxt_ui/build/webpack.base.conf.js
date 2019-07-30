var path = require('path')
var utils = require('./utils')
var config = require('../config')
var vueLoaderConfig = require('./vue-loader.conf')


function resolve(dir) {
    return path.join(__dirname, '..', dir)
}

module.exports = {
    entry: {
        app: ["babel-polyfill", "./src/main.js"]
    },
    output: {
        path: config.build.assetsRoot,
        filename: '[name].js',
        publicPath: process.env.NODE_ENV === 'production'
            ? config.build.assetsPublicPath
            : config.dev.assetsPublicPath
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            'vue$': 'vue/dist/vue.esm.js',
            '@': resolve('src'),
            'scss_vars': '@/styles/vars.scss',
            'commom_js': '@/common/js/publicUtil.js',
            '@components': resolve('src/common/components'),
            '@mixins': resolve('src/common/mixins'),
            '@directives': resolve('src/common/directives'),
            '@filters': resolve('src/common/filters'),
            '@utils': resolve('src/common/utils'),
            '@babel/polyfill': resolve('node_modules/babel-polyfill')
        }
    },
    module: {
        rules: [
            // {
            //     test: require.resolve('numbro'),
            //     loader: 'expose-loader?numbro'
            // },
            // {
            //     test: require.resolve('moment'),
            //     loader: 'expose-loader?moment'
            // },
            // {
            //     test: require.resolve('pikaday'),
            //     loader: 'expose-loader?Pikaday'
            // },
            // {
            //     test: require.resolve('zeroclipboard'),
            //     loader: 'expose-loader?ZeroClipboard'
            // },
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                options: vueLoaderConfig
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                include: [resolve('/node_modules/element-ui/src'),
                    resolve('/node_modules/element-ui/packages'),
                    resolve('/node_modules/_element-ui@2.0.7@element-ui/src'),
                    resolve('/node_modules/_element-ui@2.0.7@element-ui/packages'),
                    resolve('src'), resolve('test')]
            },
            {
                test: /\.(png|jpe?g|gif|svg|ico)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: utils.assetsPath('img/[name].[hash:7].[ext]')
                }
            },
            // {
            //     test: /\.css$/,
            //     loader: 'style-loader!css-loader',
            //     include:['node build/dev-server.js']
            // },
            {
                test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
                }
            }
        ]
    }
}
