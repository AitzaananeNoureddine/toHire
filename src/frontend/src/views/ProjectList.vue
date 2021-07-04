<template>
    <section id="general">
            <div class="top">
            <h2 style="color:white">Projects</h2>
            
            <div class="data">
            <div class="newproject">
                <span class="signal">+</span> <a href="">Create a new project</a>
            </div>
            </div>
            </div>

            <table width="100%">
                <tr v-for="project in projects" :key="project.id" @click="fetchProjectTasks(project)">
                <td>{{project.title}}</td>
                <td align="center">due on: {{project.dueOn}}</td>
                <td><input type="checkbox" id="checkbox1-1" class="regular-checkbox" /></td>
                </tr>
            </table>

            <div class="top2">
                &nbsp;     
                <div class="data2">
                <a href="" id="link_selectall">Select all</a>
                
                <a href="" id="delete_projects">Delete</a>
                </div>
            </div>
        </section>
</template>
<script>
import axios from 'axios';
export default {
    name: 'ProjectList',
    data() {
      return {
        projects: [],
      }
    },
    created() {
      axios
          .get('http://localhost:8080/api/project',{
            headers: {
              Authorization: `Bearer ${this.$cookies.get('jwt')}`,
            }
          })
          .then(response => {
              this.projects = response.data;
          })
          .catch(error => {
              console.log(error);
          });
    },
    methods: {
      fetchProjectTasks(project){
        this.$router.push({name: 'project',params: {project:project}});
        console.log("project id:");
      }
    },
};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Lato);

body {
  font-family: 'Lato', sans-serif;
}

#general {
  margin: 0 auto;
  margin-top: 50px;
  margin-bottom: 20px;
  padding: 10px;
  width: 70%;
  border: solid 1px lightgrey;
  border-radius: 4px;
  width: 100%;
}

.top {
  column-count: 2;
  column-gap: 20px;
  margin: 0 0 60px 0;
}

.top h2 {
  color: #051024 !important;
  font-size: 24px;
  margin: 0 0 40px 0;
}

.top .data .newproject {
  background: #C3D0E9;
  float: right;
  font-size: 14px;
  padding: 10px;
}

.top .data .newproject a {
  color: black;
  display: block;
  margin: -18px 0 0 20px;
  text-decoration: none;
}

table {
  background: #628AD0;
  color: white;
  border-spacing: 0;
  margin: 0;
  padding: 0;
}

table tr:hover {
  background: #C3D0E9;
  color: #fff;
  cursor: pointer;
}

table tr:hover td {
  border-bottom: 2px solid #051024;
}

table tr:hover td:first-child {
  border-left: 8px solid #051024;
}

table tr td:first-child {
  border-left: 8px solid #f7f7f7;
}

table td {
  border-bottom: 2px solid #fff;
  padding: 15px 10px 15px 25px;
}


.top .data .signal {
  color: #000;
  font-weight: bold;
}

.regular-checkbox {
  appearance: none;
  background-color: #cfd9e0;
  border: 0;
  border-radius: 3px;
  cursor: pointer;
  display: inline-block;
  float: right;
  padding: 7px;
  position: relative;
}

.regular-checkbox:checked {
  background-color: #cfd9e0;
  color: #2989e2;
}

.regular-checkbox:checked:after {
  color: #2989e2;
  content: '\2714';
  font-size: 12px;
  left: 3px;
  position: absolute;
  top:0;
}

.top2 {
  margin: 10px 0 50px 0;
}

.data2 {
  text-align: right;
}

.data2 a {
  color: #000;
  font-size: 12px;
  text-decoration: none;
}

.data2 a#link_selectall {
  padding: 30px;
}

.data2 a#delete_projects {
  background: #e2e2e2;
  border-radius: 3px;
  font-weight: bold;
  padding: 12px;
}
</style>
