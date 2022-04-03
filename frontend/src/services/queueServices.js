import axios from "axios";

export function queueUp(queueInfo) {
  return axios.post("http://localhost:8085/queue/queue-up", queueInfo);
}

export function getQueueInfoFromHashId(hashId) {
  return axios.get("http://localhost:8085/queue/course/" + hashId);
}
