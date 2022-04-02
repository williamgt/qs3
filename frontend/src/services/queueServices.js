import axios from "axios";

export function queueUp(queueInfo){
    return axios.post("http://localhost:8085/queue/queue-up", queueInfo);
}
