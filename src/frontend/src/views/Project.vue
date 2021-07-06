<template>
  <section class="section-shaped section-lg my-0">
    <div class="btn btn-flickr recruitB" @click="jobPost"><i class="fa fa-users" aria-hidden="true"></i>  Recruit</div>
    <div class="flex justify-center">
      <div class="min-h-screen flex overflow-x-scroll py-12">
        <div
          v-for="column in columns"
          :key="column.title"
          class="bg-gray-100 px-3 py-3 column-width mr-4 entry rounded"
        >
          <p class="cardTitle font-semibold font-sans tracking-wide text-sm">{{column.title.toUpperCase()}}</p>
          <draggable :list="tasks" :animation="200" ghost-class="ghost-card" group="tasks">
            <task-card
              v-for="(task) in tasks.filter((task)=>task.status===column.title)"
              :key="task.id"
              :task="task"
              class="mt-3 cursor-move"
            ></task-card>
          </draggable>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import draggable from "vuedraggable";
import TaskCard from "./components/TaskCard.vue";
import axios from "axios";
export default {
  name: "Project",
  components: {
    TaskCard,
    draggable
  },
  created() {
    axios
        .get('http://localhost:8080/api/task',{
          headers: {
            Authorization: `Bearer ${this.$cookies.get('jwt')}`,
          }
        })
        .then(response => {
            console.log(response.data);
            this.tasks = response.data;
            console.log("this.tasks"+this.tasks.length);
        })
        .catch(error => {
            console.log(error);
        });

    this.token = new URL(location.href).searchParams.get('token');
    this.$cookies.set("linkedin_token",this.token);
    console.log("linkedin_token: "+this.$cookies.get('linkedin_token'));
    axios
        .get('https://api.linkedin.com/v2/me',{
          headers: {
            Authorization: `Bearer ${this.$cookies.get('linkedin_token')}`,
            'cache-control': 'no-cache',
            'Access-Control-Allow-Origin':'*'
          }
        })
        .then(response => {
            console.log("user_id_response: "+response);
        })
        .catch(error => {
            console.log(error);
        });

  },
  data() {
    return {
      columns: [{title: "backlog",},{title: "inProgress",},{title: "review",},{title: "done",}],
      tasks: [],
      token:'',
    };
  },
  methods: {
    jobPost(){
      console.log("new job post");
      console.log(process.env.VUE_APP_LINKEDIN_CLIENT_ID);
      window.location.href = `https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=${process.env.VUE_APP_LINKEDIN_CLIENT_ID}&redirect_uri=${process.env.VUE_APP_REDIRECT_URI}&scope=${process.env.VUE_APP_LINKEDIN_SCOPES}`;
      // const headers = { 'content-type': 'application/json' };
      // axios
      //     .get(`https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=${process.env.VUE_APP_LINKEDIN_CLIENT_ID}&redirect_uri=${process.env.VUE_APP_REDIRECT_URI}&scope=${process.env.VUE_APP_LINKEDIN_SCOPES}`)
      //     .then(response => {
      //       console.log(response);
      //     })
      //     .catch(error => {
      //         console.log(error);
      //     });
    }
  },
};
</script>

<style scoped>
.column-width {
  min-width: 320px;
  width: 320px;
}
.ghost-card {
  opacity: 0.5;
  background: #F7FAFC;
  border: 1px solid #4299e1;
}
.flex{
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  overflow: visible;
}
.entry{
  width: 23%;
  margin-bottom: 30px;
  background: transparent;
  border: solid 2px #051024;
}
.cardTitle{
  color: #051024;
}
.recruitB{
  margin: 10px 0px 0px 15px;
}
</style>
