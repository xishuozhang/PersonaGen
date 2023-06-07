const { defineConfig } = require('@vue/cli-service')
const path = require('path')
const resolve = dir => path.join(__dirname, dir)
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host: '0.0.0.0',
    port: 8888,
    proxy: {
      '/': {
        target: 'http://127.0.0.1:8084',
        ws: false,
        changeOrigin: true
      },
    }
  },
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
  }
})
