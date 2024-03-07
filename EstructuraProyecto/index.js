const express = require('express');
const bodyParser = require('body-parser');

const clientsRoutes = require('./routes/clients');
const productsRoutes = require('./routes/products');
const usersRoutes = require('./routes/users');

const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use('/clients', clientsRoutes);
app.use('/products', productsRoutes);
app.use('/users', usersRoutes);

app.listen(port, () => {
    console.log(`Server running on port ${port}`);
});