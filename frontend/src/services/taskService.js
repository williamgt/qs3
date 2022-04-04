import store from "@/store";
import axios from "axios";

export function validateTasksForQueueInfoId(queueInfoId, tasks) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.put(
    "http://localhost:8085/tasks//validate/" + queueInfoId,
    tasks,
    config
  );
}
