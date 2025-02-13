import http from 'k6/http';

export const options = {
  stages: [
    { duration: '30s', target: 1000 },
  ],
};

export default function () {
  const url = 'http://localhost:8080/shorten-url/dev-5WA6u1G3JILf';

  // GET 요청 보내기
  http.get(url);
}
