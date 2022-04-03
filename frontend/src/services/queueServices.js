import axios from "axios";
import store from "@/store";

export function queueUp(queueInfo) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.post("http://localhost:8085/queue/queue-up", queueInfo, config);
}

export function getQueueInfoFromHashId(hashId) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get("http://localhost:8085/queue/course/" + hashId, config);
}
