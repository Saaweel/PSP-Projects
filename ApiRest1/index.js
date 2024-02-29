const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
let numbers = [];

// Mete 10 números random en el array
for (let i = 0; i < 10; i++) {
    numbers.push(Math.floor(Math.random() * 100));
}

app.get('/api/getNums', (req, res) => {
    res.json(numbers);
});

app.get('/api/getNumAt/:index', (req, res) => {
    const index = req.params.index;
    if (index < 0 || index >= numbers.length) {
        return res.status(404).json({ error: 'Index out of bounds' });
    }
    res.json({ index, number: numbers[index] });
});

app.post('/api/addNum', (req, res) => {
    const { number } = req.body;
    numbers.push(number);
    res.json({ message: 'Number added successfully', number });
});

app.delete('/api/deleteNum/:num', (req, res) => {
    const num = req.params.num;
    numbers = numbers.filter(n => n !== num);
    res.json({ message: 'Number deleted successfully', num });
});

app.put('/api/updateNumAt/:index', (req, res) => {
    const index = req.params.index;
    const { number } = req.body;
    if (index < 0 || index >= numbers.length) {
        return res.status(404).json({ error: 'Index out of bounds' });
    }
    numbers[index] = number;
    res.json({ message: 'Number updated successfully', index, number });
});

app.listen(PORT, () => {
    console.log(`Servidor escuchando en el puerto ${PORT}`);
});