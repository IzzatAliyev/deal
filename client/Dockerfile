FROM node:12.20.0-alpine

WORKDIR /usr/src/app

COPY package*.json /usr/src/app

RUN npm install -g @angular/cli@13.3.11

RUN npm install

COPY . /usr/src/app

EXPOSE 4200

CMD ["ng", "serve", "--host=0.0.0.0"]
