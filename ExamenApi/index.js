const express = require("express");
const bodyParser = require('body-parser');

const comprasRoutes = require('./routes/compras');
const apiComprasRoutes = require('./routes/apiCompras');

const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use("/compras", comprasRoutes);
app.use("/apiCompras", apiComprasRoutes);

app.listen(port, () => {
    console.log(`Server running on port ${port}`);
});