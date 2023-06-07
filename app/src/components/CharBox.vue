<template>
  <div class="chat-container">
    <div class="messages" ref="messages">
      <div class="message" v-for="message in messages" :key="message.id">{{ message.text }}</div>
    </div>
    <div class="input-container">
      <input type="text" v-model="input" @keyup.enter="sendMessage" placeholder="Type your message here..." />
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: "",
      messages: [],
      nextMessageId: 1,
    };
  },
  methods: {
    sendMessage() {
      const message = this.input.trim();
      if (message) {
        this.messages.push({ id: this.nextMessageId++, text: message });
        this.input = "";
        this.$nextTick(() => {
          this.$refs.messages.scrollTop = this.$refs.messages.scrollHeight;
        });
      }
    },
  },
};
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}
.messages {
  display: flex;
  flex-direction: column;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 15px;
  height: 400px;
  overflow-y: scroll;
}
.message {
  margin-bottom: 10px;
}
.input-container {
  display: flex;
  margin-top: 10px;
}
.input-container input {
  flex: 1;
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  outline: none;
}
.input-container button {
  margin-left: 5px;
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
  outline: none;
}
</style>
