const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin' );
const CleanWebpackPlugin = require('clean-webpack-plugin');
const ExtractTextPlugin = require("extract-text-webpack-plugin");

const PATHS = {
    output: path.resolve(__dirname, 'dist/'),
    app: path.resolve(__dirname, 'app/app.module.js'),
    nodeModules: path.resolve(__dirname, 'node_modules')
};

module.exports = {
    entry: {
        app: PATHS.app
    },
    output: {
        filename: '[name].bundle.js',
        path: PATHS.output,
        publicPath: './'
    },
    resolve: {
        modules: [PATHS.nodeModules]
    },
    devtool: 'source-map',
    devServer: {
        overlay: true
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'babel-loader'
            },
            {
                test: /\.html$/,
                loader: 'raw-loader'
            },
            {
                test: /\.scss$/,
                use: [
                    { loader: 'style-loader' },
                    { loader: 'css-loader' },
                    { loader: 'sass-loader' }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: 'app/index.html',
            filename: 'index.html',
            inject: 'body'
        }),
        new CleanWebpackPlugin([PATHS.output]),
        new ExtractTextPlugin('css/app.css')
    ]
}
