const path = require('path');

module.exports = {
    mode: "development",
    entry: {
        "main": "./scripts/main.ts",
        "login": "./scripts/login.ts",
        "signin": "./scripts/signin.ts"
    },
    devServer: {
        static: __dirname,
        port: 8080,
        hot: true
    },
    output: {
        filename: "[name].js",
        path: path.join(__dirname, "scripts")
    },
    resolve: {
        extensions: [".ts", ".js"]
    },
    module: {
        rules: [{
            test: /\.ts$/,
            use: "ts-loader",
            exclude: /node_modules/
        }]
    }
}