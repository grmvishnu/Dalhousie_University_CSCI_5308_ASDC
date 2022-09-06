import React, { useEffect, useState } from "react";
import "../Css/SingleTripInfo.css";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { FiChevronsRight } from "react-icons/fi";
import ShowBudget from "../Page/ShowBudget";
import ShowExpenses from "./ShowExpenses";
import { useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import API from "../utils/API";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Stack from "@mui/material/Stack";
import InputLabel from "@mui/material/InputLabel";
import Card from "@mui/material/Card";
import Typography from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Box from "@mui/material/Box";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import { URL, APP_URL } from "../utils/config.js";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import { isLoggedIn } from "../utils/API";

export default function SingleTripInfo() {
  const [data, setData] = useState({
    name: "",
    startDate: "",
    endDate: "",
    city: "",
    country: "",
    isAccepted: false,
    budgetAmount: 0,
    spentBudgetAmount: 0,
    budgetName: "",
    budgetId: 0,
    changedValueOfBudget: "",
    changedNameOfBudget: "",
    isEditFieldVisible: false,

    // ---------------------------------
    // Modal
    openBudgetModel: false,
    openExpensesModel: false,
    openCategoryModel: false,
    openExpenseEditModel: false,
    openExpenseDeleteModel: false,

    // ---------------------------------
    // Pure Content
    budgetName: "",
    budgetMaxAmt: 0,
    budgetSpentAmt: 0,
    budgetId: 0,
    categoryId: 0,
    categoryName: "",
    allCategory: [],
    allExpense: [],
    tripInfo: [],
    tripName: "",
    tripId: 0,
    tripStartDate: "",
    tripEndDate: "",
    tripCity: "",
    tripCountry: "",
    tripIsAccepted: false,
    tripIsAcceptedBy: "",

    // ------------------------------------
    // Editable content
    editedBudgetId: 0,
    editedBudgetName: "",
    editedBudgetMaxAmt: 0,
    editedBudgetSpentAmt: 0,

    // ------------------------------------
    selectCategoryId: 0,
    selectNameOfExpense: "",
    selectedDescriptionOfExpense: "",
    selectedAmountOfExpense: 0,
    selectedExpenseId: 0,
  });

  const location = useLocation();
  const navigate = useNavigate();
  const [boolBud, setBoolBud] = useState(false);
  const [boolExp, setBoolExp] = useState(false);
  const [responseBudget, setResponseBudget] = useState([]);

  let login = useSelector((state) => state.login);
  useEffect(() => {
    isLoggedIn(login, navigate);
    API()
      .get(`/trip/username/${login.id}`)
      .then((res) => {
        res.data.map((trip) => {
          if (trip.id == location.state.id) {
            setData((preState) => {
              return {
                ...preState,
                tripName: trip.name,
                tripStartDate: trip.startDate,
                tripEndDate: trip.endDate,
                tripCity: trip.city,
                tripCountry: trip.country,
                tripIsAccepted: trip.accepted == "" ? false : true,
                tripIsAcceptedBy: trip.accepted,
                tripId: trip.id,
              };
            });
          }
        });
      })
      .catch((e) => {});
  }, []);

  useEffect(() => {
    if (!data.tripIsAcceptedBy) {
      API()
        .get(`/building/${data.tripIsAcceptedBy}`)
        .then((res) => {})
        .catch((err) => {
          console.log(
            "error in getting data of host in after trip has been accepted !!"
          );
        });
    }
  }, [data.tripIsAcceptedBy]);

  useEffect(async () => {
    const response = await API().get(`/budget/${location.state.id}`);
    setResponseBudget(response.data);
    setData((preState) => {
      return {
        ...preState,
        budgetName: response.data.name,
        budgetId: response.data.id,
        budgetMaxAmt: response.data.maxAmount,
        budgetSpentAmt: response.data.amountSpent,
      };
    });
  }, [data.isEditFieldVisible]);

  useEffect(() => {
    API()
      .get(`/category/budget/${data.budgetId}`)
      .then((res) => {
        setData((preState) => {
          return {
            ...preState,
            allCategory: res.data,
          };
        });
      })
      .catch((e) => {});
  }, [data.budgetId, data.isEditFieldVisible]);

  useEffect(() => {
    if (data.selectCategoryId != "") {
      API()
        .get(`/expense/category/${data.selectCategoryId}`)
        .then((res) => {
          setData((preState) => {
            return {
              ...preState,
              allExpense: res.data,
            };
          });
        })
        .catch(() => {});
    }
  }, [data.selectCategoryId, data.isEditFieldVisible]);

  const handleBudgetClickOpen = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openBudgetModel: !data.openBudgetModel,
        };
      });
    });
  };

  const handleExpenseDeleteClickOpen = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openExpenseDeleteModel: !data.openExpenseDeleteModel,
        };
      });
    });
  };

  const handleExpenseClickOpen = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openExpensesModel: !data.openExpensesModel,
        };
      });
    });
  };

  const handleCategoryClickOpen = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openCategoryModel: !data.openCategoryModel,
        };
      });
    });
  };

  const handlePayment = () => {
    API()
      .get(`/checkout/${location.state.id}`)
      .then((data) => {
        window.location.href = URL + `/checkout/${location.state.id}`;
      })
      .catch(() => {
        console.log("Payment declined !");
      });
  };

  const handleCloseBudget = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openBudgetModel: !data.openBudgetModel,
          isEditFieldVisible: false,
        };
      });
    });

    if (data.budgetAmount != data.changedValueOfBudget) {
      API().get(`/trip/username/${location.state.id}`);
    }
  };

  const handleCloseExpenses = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openExpensesModel: !data.openExpensesModel,
        };
      });
    });

    if (data.budgetAmount != data.changedValueOfBudget) {
      API().get(`/trip/username/${location.state.id}`);
    }
  };

  const handleCloseCategory = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openCategoryModel: !data.openCategoryModel,
          selectCategoryId: 0,
          selectNameOfExpense: 0,
          selectedDescriptionOfExpense: "",
          selectedAmountOfExpense: "",
          selectedExpenseId: 0,
          editedBudgetId: 0,
          editedBudgetName: "",
          editedBudgetMaxAmt: 0,
          editedBudgetSpentAmt: 0,
          allExpense: [],
        };
      });
    });

    if (data.budgetAmount != data.changedValueOfBudget) {
      API().get(`/trip/username/${location.state.id}`);
    }
  };

  const handleExpenseDeleteConfirm = () => {
    API()
      .delete(`/expense/${data.selectedExpenseId}`)
      .then((res) => {
        setData((preState) => {
          return {
            ...preState,
            openExpenseDeleteModel: false,
          };
        });
        handleCloseCategory();
      })
      .catch((err) => {
        console.log("Error in deleting the expense is ", err);
      });
  };

  const handleCloseExpenseEdit = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openExpenseEditModel: !data.openExpenseEditModel,
          selectNameOfExpense: "",
          selectedAmountOfExpense: "",
          selectedExpenseId: "",
          selectedDescriptionOfExpense: "",
        };
      });
    });

    if (data.budgetAmount != data.changedValueOfBudget) {
      API().get(`/trip/username/${location.state.id}`);
    }
  };

  const handleExpenseDeleteClose = () => {
    setData(() => {
      setData((preState) => {
        return {
          ...preState,
          openExpenseDeleteModel: !data.openExpenseDeleteModel,
        };
      });
    });

    if (data.budgetAmount != data.changedValueOfBudget) {
      API().get(`/trip/username/${location.state.id}`);
    }
  };

  const DisplayEditOption = () => {
    setData((preState) => {
      return {
        ...preState,
        isEditFieldVisible: true,
        editedBudgetId: data.budgetId,
        editedBudgetName: data.budgetName,
        editedBudgetMaxAmt: data.budgetMaxAmt,
        editedBudgetSpentAmt: data.budgetSpentAmt,
      };
    });
  };

  const handleSaveEditExpense = async () => {
    await API()
      .put("/expense/", {
        id: data.selectedExpenseId,
        name: data.selectNameOfExpense,
        description: data.selectedDescriptionOfExpense,
        amount: data.selectedAmountOfExpense,
        category_id: data.selectCategoryId,
      })
      .then((res) => {
        setData((preState) => {
          return {
            ...preState,
            openExpenseEditModel: false,
          };
        });
      })
      .catch((err) => {
        console.log("Error is in Edit Expense :", err);
        setData((preState) => {
          return {
            ...preState,
            openExpenseEditModel: false,
          };
        });
      });
  };

  const handleSaveEditBudget = async () => {
    await API()
      .put("/budgetUpdate/", {
        id: data.editedBudgetId,
        name: data.editedBudgetName,
        amountSpent: data.editedBudgetSpentAmt,
        maxAmount: data.editedBudgetMaxAmt,
        trip_id: data.tripId,
      })
      .then((res) => {
        setData((preState) => {
          return {
            ...preState,
            isEditFieldVisible: false,
            openBudgetModel: false,
          };
        });
      })
      .catch((err) => {
        console.log("Error in edit the budget info ", err);
        setData((preState) => {
          return {
            ...preState,
            isEditFieldVisible: false,
            openBudgetModel: false,
          };
        });
      });
  };

  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
    padding: theme.spacing(1),
    textAlign: "center",
  }));

  return (
    <div>
      <TravelerHomePageHeader />
      <Card sx={{ display: "flex", background: "aliceblue" }}>
        <img
          style={{ width: "50vw", height: "100vh" }}
          src="https://cdn.dal.ca/dept/summer-accommodations/halifax-accommodations/_jcr_content/contentPar/staticimage.adaptive.full.high.jpg/1479386329520.jpg"
        ></img>
        <div style={{ margin: "auto" }}>
          <h1 style={{ marginBottom: "5%" }}> Information Of Trip !!</h1>
          <div
            style={{
              left: "50%",
              transform: "translateX(-50%)",
              position: "relative",
            }}
          >
            <Stack spacing={2}>
              <Item>Trip name : {data.tripName}</Item>
              <Item>
                Start From : {data.tripStartDate} {"  to  "} {data.tripEndDate}
              </Item>
              <Item>City :{data.tripCity}</Item>
              <Item>Country : {data.tripCountry}</Item>
            </Stack>
          </div>

          <br></br>
          <div
            style={{
              display: "grid",
              alignContent: "space-around",
            }}
          >
            <Button variant="contained" onClick={handleBudgetClickOpen}>
              Budget
            </Button>
            <Button
              sx={{ marginTop: "5%" }}
              variant="contained"
              onClick={handleCategoryClickOpen}
            >
              Expense
            </Button>

            {data.tripIsAccepted && (
              <Button
                sx={{ marginTop: "5%" }}
                variant="contained"
                onClick={handlePayment}
              >
                Pay the Trip
              </Button>
            )}

            {data.tripIsAccepted && (
              <div>
                <Button
                  sx={{ marginTop: "5%", width: "100%" }}
                  variant="contained"
                  onClick={() => {
                    navigate(PATH.Traveller.HOSTPROFILECREATION, {
                      state: { disable: true, hostId: data.tripIsAcceptedBy },
                    });
                  }}
                >
                  Place Detail
                </Button>
                <br></br>

                <Button
                  sx={{ marginTop: "5%", width: "100%" }}
                  variant="contained"
                  onClick={() => {
                    navigate(PATH.Traveller.USERPROFILE, {
                      state: { disable: true, hostId: data.tripIsAcceptedBy },
                    });
                  }}
                >
                  Host Detail
                </Button>
              </div>
            )}

            {/* 
            Budget Information 
        */}
            <Dialog
              fullWidth
              open={data.openBudgetModel}
              onClose={handleCloseBudget}
            >
              <DialogTitle
                sx={{
                  textAlign: "center",
                }}
              >
                Budget Information
              </DialogTitle>
              <hr></hr>

              <DialogContent>
                <DialogContentText>
                  {"Budget name: "}
                  {data.budgetName}
                </DialogContentText>
                <DialogContentText>
                  {"Maximum amount is: $"}
                  {data.budgetMaxAmt}
                </DialogContentText>
                <DialogContentText>
                  {"You have spent  : $"}
                  {data.budgetSpentAmt}
                </DialogContentText>
                <br></br>
                <Button variant="outlined" onClick={DisplayEditOption}>
                  Edit Max. Budget
                </Button>
                {data.isEditFieldVisible ? (
                  <div>
                    <TextField
                      autoFocus
                      margin="dense"
                      id="name"
                      label="Change the Maximum budget: "
                      type="number"
                      fullWidth
                      variant="standard"
                      value={data.editedBudgetMaxAmt}
                      onChange={(e) => {
                        setData((preState) => {
                          return {
                            ...preState,
                            editedBudgetMaxAmt: e.target.value,
                          };
                        });
                      }}
                    />
                    <TextField
                      autoFocus
                      margin="dense"
                      id="name"
                      label="Change the budget Name: "
                      type="text"
                      fullWidth
                      variant="standard"
                      value={data.editedBudgetName}
                      onChange={(e) => {
                        setData((preState) => {
                          return {
                            ...preState,
                            editedBudgetName: e.target.value,
                          };
                        });
                      }}
                    />
                    <TextField
                      autoFocus
                      margin="dense"
                      id="name"
                      label="Change the Total Spent: "
                      value={data.editedBudgetSpentAmt}
                      type="number"
                      fullWidth
                      variant="standard"
                      onChange={(e) => {
                        setData((preState) => {
                          return {
                            ...preState,
                            editedBudgetSpentAmt: e.target.value,
                          };
                        });
                      }}
                    />
                  </div>
                ) : null}
              </DialogContent>
              <DialogActions>
                <Button onClick={handleCloseBudget}>Cancel</Button>
                <Button onClick={handleSaveEditBudget}>Save</Button>
              </DialogActions>
            </Dialog>
            <br></br>

            {/* 
            Category modal
            */}

            <Dialog
              fullWidth
              open={data.openCategoryModel}
              onClose={handleCloseCategory}
            >
              <DialogTitle
                sx={{
                  textAlign: "center",
                }}
              >
                Category Information
              </DialogTitle>
              <hr></hr>

              {data.selectCategoryId == 0 && (
                <DialogContentText
                  sx={{
                    textAlign: "center",
                    marginTop: "5px",
                  }}
                >
                  Please select the category !!
                </DialogContentText>
              )}

              <DialogContent>
                <Select
                  labelId="select"
                  label="Category"
                  onChange={(e) => {
                    setData((preState) => {
                      return {
                        ...preState,
                        selectCategoryId: e.target.value,
                      };
                    });
                  }}
                >
                  {data.allCategory.map((category, index) => {
                    return (
                      <MenuItem key={index} value={category.id}>
                        {category.name}
                      </MenuItem>
                    );
                  })}
                </Select>

                {data.allExpense.length != 0 ? (
                  data.allExpense.map((expense) => {
                    return (
                      <div>
                        <br></br>
                        <Card sx={{ minWidth: 275 }} variant="outlined">
                          <CardContent>
                            <Typography
                              sx={{ fontSize: 14 }}
                              color="text.primary"
                            >
                              Title: {expense.name}
                              <br></br>
                              Amount: {expense.amount}
                              <br></br>
                              Description: {expense.description}
                            </Typography>
                          </CardContent>
                          <CardActions>
                            <div style={{ display: "flex" }}>
                              <Button
                                size="small"
                                onClick={() => {
                                  setData((preState) => {
                                    return {
                                      ...preState,
                                      openExpenseEditModel:
                                        !data.openExpenseEditModel,
                                      selectNameOfExpense: expense.name,
                                      selectedAmountOfExpense: expense.amount,
                                      selectedDescriptionOfExpense:
                                        expense.description,
                                      selectedExpenseId: expense.id,
                                    };
                                  });
                                }}
                              >
                                Edit
                              </Button>
                              <Button
                                size="small"
                                onClick={() => {
                                  setData((preState) => {
                                    return {
                                      ...preState,
                                      selectedExpenseId: expense.id,
                                      openExpenseDeleteModel:
                                        !data.openExpenseDeleteModel,
                                    };
                                  });
                                }}
                              >
                                Delete
                              </Button>
                            </div>
                          </CardActions>
                        </Card>
                      </div>
                    );
                  })
                ) : (
                  <DialogContentText
                    sx={{
                      textAlign: "center",
                      marginTop: "7px",
                      color: "#1976d2",
                    }}
                  >
                    No Expense !!
                  </DialogContentText>
                )}
              </DialogContent>
              <DialogActions>
                <Button onClick={handleCloseCategory}>Cancel</Button>
              </DialogActions>
            </Dialog>
            <br></br>

            {/* 
            Edit Expense Modal 
           */}
            <Dialog
              fullWidth
              open={data.openExpenseEditModel}
              onClose={handleCloseExpenseEdit}
            >
              <DialogTitle sx={{ textAlign: "center" }}>
                Edit Your Expense
              </DialogTitle>
              <hr></hr>

              <DialogContent>
                <TextField
                  autoFocus
                  margin="dense"
                  id="name"
                  label="Name of Expense: "
                  type="text"
                  fullWidth
                  variant="standard"
                  value={data.selectNameOfExpense}
                  onChange={(e) => {
                    setData((preState) => {
                      return {
                        ...preState,
                        selectNameOfExpense: e.target.value,
                      };
                    });
                  }}
                />

                <br></br>
                <TextField
                  autoFocus
                  margin="dense"
                  id="name"
                  label="Description of Expense: "
                  type="text"
                  fullWidth
                  variant="standard"
                  value={data.selectedDescriptionOfExpense}
                  onChange={(e) => {
                    setData((preState) => {
                      return {
                        ...preState,
                        selectedDescriptionOfExpense: e.target.value,
                      };
                    });
                  }}
                />
                <br></br>
                <TextField
                  autoFocus
                  margin="dense"
                  id="name"
                  label="Amount of Expense: "
                  type="number"
                  fullWidth
                  variant="standard"
                  value={data.selectedAmountOfExpense}
                  onChange={(e) => {
                    setData((preState) => {
                      return {
                        ...preState,
                        selectedAmountOfExpense: e.target.value,
                      };
                    });
                  }}
                />
              </DialogContent>
              <DialogActions>
                <Button onClick={handleCloseExpenseEdit}>Cancel</Button>
                <Button onClick={handleSaveEditExpense}>Save</Button>
              </DialogActions>
            </Dialog>
            <br></br>

            {/* 
            Delete Confirmation Expense Model 
            */}
            <Dialog
              open={data.openExpenseDeleteModel}
              onClose={handleExpenseDeleteClose}
            >
              <DialogTitle>Do really want to Delete the expense ?</DialogTitle>
              <DialogActions>
                <Button onClick={handleExpenseDeleteClose}>Cancel</Button>
                <Button onClick={handleExpenseDeleteConfirm}>Delete</Button>
              </DialogActions>
            </Dialog>
            <br></br>

            {/* 
            host info 
            */}
          </div>
        </div>
      </Card>
    </div>
    // </div>
  );
}
