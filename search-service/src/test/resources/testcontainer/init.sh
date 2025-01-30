curl -X PUT "http://$1/blogpost" \
-H "Content-Type: application/json" \
-d '{
      "mappings": {
        "properties": {
          "content": {
            "type": "text",
            "index": "true"
          },
          "title": {
            "type": "text",
            "index": "true"
          }
        }
      }
    }'