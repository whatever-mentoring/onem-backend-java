import http from 'k6/http';

export const options = {
  stages: [
    { duration: '30s', target: 300 },
  ],
};

export default function () {
  const url = 'http://shortenurl:8080/shorten-url/dev-ah31k1g2h1k';

  http.get(url);
}
