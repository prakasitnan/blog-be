docker build -t blog-be:0.1.0 .
docker run -d --name blog-be --link bfdfd825146b -p 8181:8080 -v /Users/prakasit/DATA-STORAGE:/usr/DATA-STORAGE blog-be