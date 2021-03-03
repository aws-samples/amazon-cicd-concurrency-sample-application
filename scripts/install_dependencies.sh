#!/bin/bash
mkdir -p /home/ec2-user/express-service
sudo rm -rf /home/ec2-user/express-service
mkdir /home/ec2-user/express-service
cd /home/ec2-user/express-service

sudo yum install -y java-1.8.0-openjdk-devel.x86_64
sudo yum erase -y java-1.7.0-openjdk



