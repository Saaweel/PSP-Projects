const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const PORT = 3000;

app.use(bodyParser.json());

let tasks = [];

app.get('/tasks', (req, res) => {
    res.json(tasks);
});

app.get('/tasks/:id', (req, res) => {
    const taskId = req.params.id;
    const task = tasks.find(task => task.id === taskId);
    if (!task) {
        return res.status(404).json({ message: 'Tarea no encontrada' });
    }
    res.json(task);
});

app.post('/tasks', (req, res) => {
    const newTask = req.body;
    tasks.push(newTask);
    res.status(201).json(newTask);
});

app.put('/tasks/:id', (req, res) => {
    const taskId = req.params.id;
    const updatedTask = req.body;
    let taskIndex = tasks.findIndex(task => task.id === taskId);
    if (taskIndex === -1) {
        return res.status(404).json({ message: 'Tarea no encontrada' });
    }
    tasks[taskIndex] = { ...tasks[taskIndex], ...updatedTask };
    res.json(tasks[taskIndex]);
});

app.delete('/tasks/:id', (req, res) => {
    const taskId = req.params.id;
    tasks = tasks.filter(task => task.id !== taskId);
    res.status(204).end();
});

app.listen(PORT, () => {
    console.log(`Servidor escuchando en el puerto ${PORT}`);
});
