const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;

let seats = Array(50).fill(false);

app.use(bodyParser.json());

app.get('/seats', (req, res) => {
  res.json({ seats });
});

app.post('/reserve', (req, res) => {
  const seatNumber = req.body.seatNumber;

  if (seats[seatNumber]) {
    return res.status(400).json({ error: 'El asiento ya está ocupado' });
  }

  seats[seatNumber] = true;
  res.json({ message: `Asiento ${seatNumber} reservado exitosamente` });
});

app.listen(PORT, () => {
  console.log(`Servidor iniciado en el puerto ${PORT}`);
});