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

export function taGetActiveQueues(taId) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get(
    "http://localhost:8085/queue/ta-active-queue/" + taId,
    config
  );
}

export function taGetInactiveQueues(taId) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get(
    "http://localhost:8085/queue/ta-inactive-queue/" + taId,
    config
  );
}

export function taActivateOrDeactivateQueue(hashId) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.put(
    "http://localhost:8085/queue/activate-or-deactivate/" + hashId,
    config
  );
}

/*export function getTasksRelatedToHashId(hashId, user) {
  let payload = { hashId, user };
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get()
}*/
