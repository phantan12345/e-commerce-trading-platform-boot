#!/bin/bash

# Đường dẫn đến thư mục chứa file docker-compose.yml
DOCKER_COMPOSE_DIR="e-commerce-trading-platform"

# Di chuyển đến thư mục chứa file docker-compose.yml
cd "$DOCKER_COMPOSE_DIR" || exit

# Kiểm tra xem docker-compose đã được cài đặt
if ! command -v docker-compose &> /dev/null; then
    echo "docker-compose không được tìm thấy. Vui lòng cài đặt docker-compose trước khi tiếp tục."
    exit 1
fi

# Chạy docker-compose
docker-compose up -d
