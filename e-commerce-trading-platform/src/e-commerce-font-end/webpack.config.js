const path = require('path') // lấy đường dẫn tuyệt đối của thư mục



const config = {
    entry: './src/index.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'build')
    }
}
module.exports = config;